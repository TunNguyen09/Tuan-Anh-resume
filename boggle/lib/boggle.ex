defmodule Boggle do

  @moduledoc """
    Add your boggle function below. You may add additional helper functions if you desire.
    Test your code by running 'mix test' from the tester_ex_simple directory.
  """

  def boggle(board, words) do

    words_set = Enum.into(words, MapSet.new())

    board_size = tuple_size(board)

    Enum.reduce(words_set, %{}, fn word, acc ->
      Enum.reduce(0..(board_size - 1), acc, fn i, acc2 ->
        Enum.reduce(0..(board_size - 1), acc2, fn j, acc3 ->
          if String.starts_with?(word, elem(elem(board, i), j)) do
            dfs(board, word, i, j, board_size, 0, "", [], acc3, MapSet.new())
          else
            acc3
          end
        end)
      end)
    end)
  end

  def dfs(board, word, i, j, n, idx, str, arr, dict, visited) do

    new_visited = MapSet.put(visited, {i, j})

    found_str = str <> elem(elem(board, i), j)

    cond do
      found_str == word ->
        Map.put(dict, word, Enum.reverse([{i, j} | arr]))

      String.starts_with?(word, found_str) ->
        Enum.reduce([{-1, 1}, {0, 1}, {1, 1}, {-1, 0}, {1, 0}, {-1, -1}, {0, -1}, {1, -1}], dict, fn {di, dj}, res ->
          new_i = i + di
          new_j = j + dj

          if (0 <= new_i) and (new_i < n) and (0 <= new_j) and (new_j < n) and not MapSet.member?(new_visited, {new_i, new_j}) do
            dfs(board, word, new_i, new_j, n, idx + 1, found_str, [{i, j} | arr], res, new_visited)
          else
            res
          end
        end)

      true -> dict
    end
  end

end
